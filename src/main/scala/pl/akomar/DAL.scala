package pl.akomar

import com.typesafe.scalalogging.LazyLogging

import scala.slick.driver.H2Driver.simple._
import pl.akomar.domain.Invitee

import scala.slick.driver.H2Driver
import scala.slick.jdbc.JdbcBackend.Database
import scala.slick.lifted.{ProvenShape, Tag}

//Data Access Layer

trait InviteeRepository {
  def getInvitees(): List[Invitee]

  def addInvitee(invitee: Invitee): Invitee
}

object InviteeRepository extends LazyLogging {
  def configure(): InviteeRepository = {
    logger.info("Configuring Invitee repository...")
    import com.typesafe.config.ConfigFactory
    val conf = ConfigFactory.load()
    val repoParam = conf.getString("invitee.repository.impl")

    if (repoParam == "H2DB") {
      logger.info("Creating H2DBInviteeRepository")
      val h2Repo = new H2DBInviteeRepository
      h2Repo.createDB()
      h2Repo
    }
    else {
      logger.info("Creating ImmutableInviteeRepository")
      new ImmutableInviteeRepository
    }
  }
}

class H2DBInviteeRepository extends InviteeRepository with LazyLogging {
  val invitees: TableQuery[InviteesTable] = TableQuery[InviteesTable]
  val db = Database.forURL("jdbc:h2:mem:invitationdb", driver = "org.h2.Driver")
  implicit val implicitSession = db.createSession

  def createDB(): Unit = try {
    invitees.ddl.create
    logger.info("H2DB created")
  } catch {
    case e: Exception => logger.info("Could not create database, assuming it already exists")
  }

  def dropDB(): Unit = try {
    invitees.ddl.drop
  } catch {
    case e: Exception => logger.info("Could not drop database")
  }

  def getInvitees(): List[Invitee] = {
    logger.debug("Getting all invitees...")
    val result = invitees.list
    logger.debug("Got invitees: " + result)
    result
  }

  def addInvitee(invitee: Invitee): Invitee = {
    val result = invitees.insert(invitee)
    println("Inserted person: " + result)
    invitee.copy()
  }

}

class ImmutableInviteeRepository extends InviteeRepository {
  def getInvitees(): List[Invitee] = {
    List(Invitee("John Smith", "john@smith.mx"))
  }

  def addInvitee(invitee: Invitee): Invitee = {
    invitee.copy()
  }
}

class InviteesTable(tag: Tag) extends Table[Invitee](tag, "INVITEES") {
  def invitee = column[String]("invitee")

  def email = column[String]("email")

  def * = (invitee, email) <>(Invitee.tupled, Invitee.unapply)

}