package no.bekk.liftworkshop.model

class Game(val playerA: String, val playerB: String) {
  val cells: Array[String] = Array[String]("", "", "", "", "", "", "", "", "")

  def makeMove(player: String, cell: Int) {
    cells.update (cell, if(player.equals(playerA)) "X" else "O")
  }
}