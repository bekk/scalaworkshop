package no.bekk.liftworkshop.snippet

import controller.LobbyActor
import net.liftweb._
import http._
import util._
import js._
import JsCmds._
import JE._
import SHtml._
import BindHelpers._
import xml.{Text, NodeSeq}


class Lobby extends  {
  def form(html: NodeSeq) = {
    var username = ""

    bind("login", html,
      "username" -> text("", username = _),
      "submit" -> submit("Log in", () => {
        LobbyActor.addUser(username)
      }))
  }

  def render(html: NodeSeq) = {
    var users = LobbyActor.users.toSeq

    var list = users.flatMap(user =>
        bind("user", chooseTemplate("users", "list", html),
          "username" -> Text(user)
        ))

    bind("users", html,
      "list" -> list
    )
  }
}