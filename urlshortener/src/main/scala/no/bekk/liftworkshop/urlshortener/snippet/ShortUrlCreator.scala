package no.bekk.liftworkshop.urlshortener.snippet

import model.ShortenedUrl
import net.liftweb._
import http._
import SHtml._
import S._

import js._
import JsCmds._

import mapper._

import util._
import Helpers._

import scala.xml.{NodeSeq, Text}

/**
 * 
 */

class ShortUrlCreator {
  def form(xhtml: NodeSeq) = {
    var url = ""
//    bind("creator", xhtml,
//      "url" -> text(url, url = _),
//      "submit" -> submit("Shorten URL", () => {
//        print(url)
//        val shortenedUrl = new ShortenedUrl()
//        shortenedUrl.url(url)
//        shortenedUrl.generateToken
//        shortenedUrl.save
//      })
//    );
    new ShortenedUrl().toForm(Full("Create url"), (u:ShortenedUrl) => {u.generateToken; u.save})
  }

  def list(xhtml: NodeSeq) = {
    var urls = ShortenedUrl.findAll

    <ul>
      {urls.map((url:ShortenedUrl) => {
        <li><a href={url.url.toString}>{url.token}</a> ({url.url})</li>
      })}
    </ul>
  }
}