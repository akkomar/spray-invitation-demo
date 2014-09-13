package pl.akomar

import com.typesafe.scalalogging.LazyLogging

import scala.slick.driver.H2Driver.simple._
import pl.akomar.domain.Invitee

import scala.slick.driver.H2Driver
import scala.slick.jdbc.JdbcBackend.Database
import scala.slick.lifted.{ProvenShape, Tag}

//Data Access Layer

trait H2DB extends LazyLogging {
  val invitees: TableQuery[InviteesTable] = TableQuery[InviteesTable]
  val db = Database.forURL("jdbc:h2:mem:invitationdb", driver = "org.h2.Driver")
  implicit val session = db.createSession()

  //    val m = new Model("H2", new DAL(H2Driver),
  //      Database.forURL("jdbc:h2:mem:invitationdb", driver = "org.h2.Driver"))
  //    m.createDB

  def createDB(): Unit = try {
    invitees.ddl.create
  } catch {
    case e: Exception => logger.info("Could not create database, assuming it already exists")
  }

  def dropDB(): Unit = try {
    invitees.ddl.drop
  } catch {
    case e: Exception => logger.info("Could not drop database")
  }

  def getInvitees(): List[Invitee] = {
    val result = invitees.list
    println("Got invitees: " + result)
    result
  }

  def addInvitee(invitee: Invitee)/*: Invitee*/ = {
    val result = invitees.insert(invitee)
    println("Inserted person: " + result)
    result
  }

}

class InviteesTable(tag: Tag) extends Table[Invitee](tag, "INVITEES") {
  def invitee = column[String]("invitee")

  def email = column[String]("email")

  def * = (invitee, email) <>(Invitee.tupled, Invitee.unapply)

}