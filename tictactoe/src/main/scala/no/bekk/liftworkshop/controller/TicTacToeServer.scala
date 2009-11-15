package no.bekk.liftworkshop.controller

import collection.mutable.{HashSet, HashMap}
import comet.TicTacToeClient
import net.liftweb.http.ListenerManager
import scala.actors.Actor
import model.Game


case class UpdateClient
case class CreateGame(playerA:String, playerB:String)
case class JoinGame(game: Game)
case class LeaveGame(game: Game)
case class MakeMove(player: String, game: Game, cell: Int)
case class AddUser(username: String, clientActor: TicTacToeClient)
case class EndGame(game: Game)

object TicTacToeServer extends Actor with ListenerManager {
  var activeGames = HashSet[Game]()
  var users = HashMap[String, TicTacToeClient]()

  def createUpdate = UpdateClient

  override def lowPriority = {
    case CreateGame(playerA: String, playerB: String) => createGame(playerA, playerB)
    case MakeMove(player: String, game: Game, cell: Int) => makeMove(player, game: Game, cell)
    case AddUser(username: String, clientActor: TicTacToeClient) => addUser(username, clientActor)
    case EndGame(game: Game) => endGame(game)
  }

  def addUser(username: String, clientActor: TicTacToeClient) = {
    users.put(username, clientActor)
    println("Added user " + username)
    updateListeners
  }


  def createGame(playerA: String, playerB: String) = {
    println("Creating game " + playerA + " vs " + playerB)
    var game = new Game(playerA, playerB)
    updateGame(game)
    activeGames.incl(game)
    game
  }

  def makeMove(player: String, game: Game, cell: Int) = {
    game.makeMove(player, cell)
    updateGame(game)
  }

  def endGame(game: Game) = {
    activeGames.excl(game)

    users.get(game.playerA) match {
      case None => false
      case Some(client) => client ! LeaveGame(game)
    }
    users.get(game.playerB) match {
      case None => false
      case Some(client) => client ! LeaveGame(game)
    }
  }

  def updateGame(game: Game) = {
    users.get(game.playerA) match {
      case None => false
      case Some(client) => client ! JoinGame(game)
    }
    users.get(game.playerB) match {
      case None => false
      case Some(client) => client ! JoinGame(game)
    }
  }

  // Siden dette er et object må TicTacToeServer starte seg selv
  start  
}