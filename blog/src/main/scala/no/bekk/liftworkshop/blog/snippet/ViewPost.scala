package no.bekk.liftworkshop.blog.snippet

import xml.NodeSeq
import net.liftweb.util.Helpers._

import scala.xml.{NodeSeq, Text}
import no.bekk.liftworkshop.blog.model.Post
import net.liftweb.http.S

/**
 * 
 */
class ViewPost {
  var id = S.param("id") openOr ""

  def render(html: NodeSeq) = {
    val post = Post.findByKey(id.toLong).open_!

    
    bind("post", html,
      "title" -> post.title,
      "summary" -> post.summary,
      "body" -> post.body,
      "createdAt" -> post.createdAt
    )
  }
}