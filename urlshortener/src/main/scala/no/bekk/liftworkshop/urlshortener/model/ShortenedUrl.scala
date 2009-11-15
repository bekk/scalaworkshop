package no.bekk.liftworkshop.urlshortener.model

import _root_.net.liftweb.mapper._
import java.util.Random
import net.liftweb.http.FieldError
import xml.Text

/**
 * 
 */

class ShortenedUrl extends LongKeyedMapper[ShortenedUrl] with IdPK {
  def getSingleton = ShortenedUrl

  object url extends MappedString(this, 255) {
    def isValidUrl(url: String) = {
      var UrlPattern = "https?://.*".r
      if (UrlPattern != url) {
        List(FieldError(this, Text("'" + url + "' is not a valid url")))
      } else {
        List[FieldError]()
      }
    }

    override def validations = isValidUrl _ :: Nil 
  }
  
  object token extends MappedString(this, 20) {
    
  }

  def generateToken {
    var newToken = ""
    val symbols = "abcdefghijklmnopqrstuvwxyz1234567890"
    var rand = new Random(System.currentTimeMillis)
    
    for (i <- 0.until(6)) {
      newToken += symbols(rand.nextInt(symbols.length-1))
    }

    this.token(newToken)
  }
}

object ShortenedUrl extends ShortenedUrl with LongKeyedMetaMapper[ShortenedUrl] {
  
}
