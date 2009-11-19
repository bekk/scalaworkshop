package no.bekk.liftworkshop.blog.snippet


import model.{Post, Comment}
import net.liftweb.http.S
import net.liftweb.util.Full
import xml.NodeSeq

/**
 * 
 */

class CreateComment {
  var id = S.param("id") openOr ""

  def render(html: NodeSeq) = {
    val post = Post.findByKey(id.toLong).open_!
    var comment = Comment.create

    comment.toForm(Full("Submit"), (comment: Comment) => {
      comment.post(post)      
      comment.save
      S.notice("Comment submitted")
    })
  }
}