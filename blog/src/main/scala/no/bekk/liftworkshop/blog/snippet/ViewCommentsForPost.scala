package no.bekk.liftworkshop.blog.snippet


import model.Post
import net.liftweb.http.S
import xml.NodeSeq
import net.liftweb.util.Helpers._


/**
 * 
 */

class ViewCommentsForPost {
  var id = S.param("id") openOr ""

  def render(html: NodeSeq): NodeSeq = {
    val post = Post.findByKey(id.toLong).open_!

    if (post.comments.length == 0) {
      return <li>No comments yet!</li>
    }

    post.comments.flatMap(comment => {
      bind("comment", html,
        "title" -> comment.title,
        "body" -> comment.body
      )
    })

  }
}