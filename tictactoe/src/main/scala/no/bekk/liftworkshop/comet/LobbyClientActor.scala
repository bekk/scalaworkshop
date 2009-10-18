package no.bekk.liftworkshop.comet

import controller.{UpdateUserList, LobbyActor}
import net.liftweb.http.CometActor
import net.liftweb._
import http._
import util._
import js._
import JsCmds._
import JE._
import SHtml._
import BindHelpers._
import xml.{Text, NodeSeq}

class LobbyClientActor extends CometActor with CometListenee {
  var username:String = ""
  protected def registerWith = LobbyActor

  def render = {
    if(username.equals("")) {
      ajaxForm(
        bind("login", chooseTemplate("client", "login", defaultXml),
          "username" -> text("", username = _),
          "submit" -> submit("Log in", () => {})) ++
        hidden(() => {
          LobbyActor.addUser(username)
          reRender(false)
        })
      )
    } else {
      // Fetch active users from Lobby, and filter out current user
      println("Updating user list for user " + username)
      var users = LobbyActor.users.filter(user => !user.equals(username)).toSeq

      var list = users.flatMap(user =>
        bind("list", chooseTemplate("lobby", "list", defaultXml),
          "username" -> Text(user)
        )
      )

      bind("lobby", chooseTemplate("client", "lobby", defaultXml),
        "list" -> list,
        "currentUser" -> Text(username)
      )
    }
  }

  override def lowPriority = {
    case UpdateUserList =>
      reRender(false)
  }
}