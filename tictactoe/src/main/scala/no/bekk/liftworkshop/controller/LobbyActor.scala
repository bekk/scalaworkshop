package no.bekk.liftworkshop.controller

import collection.mutable.HashSet
import net.liftweb.http.ListenerManager
import scala.actors.Actor
import scala.actors.Actor._
import model.Game


case class UpdateUserList

object LobbyActor extends Actor with ListenerManager {
  var activeGames = List[Game]()
  var users = HashSet[String]()

  def createUpdate = UpdateUserList

  def addUser(username: String) = {
    users.incl(username)
    println("Added user " + username)
    updateListeners
  }

  // Siden dette er et object må LobbyActor starte seg selv
  start  
}