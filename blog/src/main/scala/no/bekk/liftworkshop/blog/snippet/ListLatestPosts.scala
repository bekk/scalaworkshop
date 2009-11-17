package no.bekk.liftworkshop.blog.snippet

import no.bekk.liftworkshop.blog.model.Post
import net.liftweb.util.Helpers._
import xml.{Text, NodeSeq}

/**
 * 
 */

class ListLatestPosts {
  def render(html: NodeSeq): NodeSeq = {
    val posts = Post.findAll

    posts.flatMap(post =>
      bind("post", html,
        "title" -> post.title,
        "summary" -> post.summary,
        "createdAt" -> post.createdAt,
        FuncAttrBindParam("view_href", _ => Text("/post/view/" + post.primaryKeyField), "href")
    ))
  }
}