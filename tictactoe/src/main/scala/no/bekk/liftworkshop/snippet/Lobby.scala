package no.bekk.liftworkshop.snippet

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
  def render(html: NodeSeq) = {
    var users = List("User 1", "User 2")

    bind("users", html,
      "test" -> Text("test"),
      "list" -> users.flatMap(user =>
        bind("user", chooseTemplate("users", "list", html),
          "username" -> Text(user)
        )
      )
    )
  }
}