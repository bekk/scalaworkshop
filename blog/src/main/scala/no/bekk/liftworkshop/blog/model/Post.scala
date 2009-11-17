package no.bekk.liftworkshop.blog.model

import net.liftweb.mapper._
import net.liftweb.http.SHtml._
import net.liftweb.util.{Full, Empty}
import java.util.Date
import xml.{Text, NodeSeq}

/**
 * 
 */

class Post extends LongKeyedMapper[Post] with IdPK {
  def getSingleton = Post

  object title extends MappedString(this, 100) {
    override def displayName = "Title"
  }

  object summary extends MappedText(this) {
    override def displayName = "Summary"
  }

  object body extends MappedText(this) {
    override def displayName = "Body"
    override def _toForm = Full(textarea(is, set(_)))
  }

  object createdAt extends MappedDateTime(this) {
    override def displayName = "Created at"
  }
}

object Post extends Post with LongKeyedMetaMapper[Post] with CRUDify[Long, Post] {
  override def fieldOrder = title :: summary :: body :: Nil
  override def viewMenuLoc = Empty
 
  override def beforeCreate = {
    List((post: Post) => {
      post.createdAt.setFromAny(new Date)
    })
  }
}