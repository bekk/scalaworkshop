package no.bekk.liftworkshop.blog.model

import net.liftweb.mapper._

/**
 * 
 */

class Post extends LongKeyedMapper[Post] with IdPK {
  def getSingleton = Post
  object createdAt extends MappedDateTime(this)
  object title extends MappedString(this, 100)
  object summary extends MappedText(this)
  object body extends MappedText(this)
}

object Post extends Post with LongKeyedMetaMapper[Post] with CRUDify[Long, Post]