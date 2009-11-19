package no.bekk.liftworkshop.blog.snippet

import xml.NodeSeq
import net.liftweb.util.Helpers._

import scala.xml.{NodeSeq, Text}
import no.bekk.liftworkshop.blog.model.Post
import net.liftweb.http.S
import net.liftweb.http.SHtml._


class CreatePost {
  def render(html: NodeSeq) = {
    val post = Post.create

    bind("post", html,
      "title" -> text("", (newTitle: String) => {
        post.title(newTitle)
      }),
      "summary" -> text("", post.summary(_)),
      "body" -> post.body.toForm,
      "submit" -> submit("Save", () => {
        post.save
        S.notice("Post " + post.title + " created")
      })
    );
  }
}