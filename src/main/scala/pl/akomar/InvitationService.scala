package pl.akomar

import akka.actor.Actor.Receive
import akka.actor.{ActorRefFactory, Actor}
import com.typesafe.scalalogging.LazyLogging
import pl.akomar.domain.Invitee
import spray.http.StatusCodes
import spray.httpx.marshalling.ToResponseMarshallable
import spray.routing.HttpService


class InvitationServiceActor extends Actor with InvitationService with LazyLogging {
  logger.debug("Creating InvitationServiceActor")

  val inviteeRepository = InviteeRepository.configure()

  def receive: Receive = runRoute(invitationRoutes)

  def actorRefFactory: ActorRefFactory = context
}

trait InvitationService extends HttpService with LazyLogging {
  val inviteeRepository: InviteeRepository

  import pl.akomar.domain.InviteeFormatter._
  import spray.httpx.SprayJsonSupport._

  val invitationRoutes =
    path("invitation") {
      get {
        complete {
          logger.info("Received GET /invitation")
          val allInvitees: ToResponseMarshallable = inviteeRepository.getInvitees()
          allInvitees
        }
      } ~
        post {
          entity(as[Invitee]) { invitee =>
            logger.info(s"Received invitee: $invitee")
            inviteeRepository.addInvitee(invitee)
            complete(StatusCodes.Created)
          }
        }
    }
}
