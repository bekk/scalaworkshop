package no.bekk.liftworkshop.blog.model


import net.liftweb.mapper._
import net.liftweb.util.Full
import net.liftweb.http.SHtml._

class Comment extends LongKeyedMapper[Comment] with IdPK {
  def getSingleton = Comment

  object title extends MappedString(this, 100) {
    override def displayName = "Title"
  }

  object body extends MappedText(this) {
    override def displayName = "Body"
    override def _toForm = Full(textarea(is, set(_)))
  }

  object post extends MappedLongForeignKey(this, Post)
}

object Comment extends Comment with LongKeyedMetaMapper[Comment] with CRUDify[Long, Comment] {
  override def fieldOrder = title :: body :: Nil
}