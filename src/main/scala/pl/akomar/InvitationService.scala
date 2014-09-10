package pl.akomar

import akka.actor.Actor.Receive
import akka.actor.{ActorRefFactory, Actor}
import com.typesafe.scalalogging.LazyLogging
import pl.akomar.domain.Invitee
import spray.http.StatusCodes
import spray.routing.HttpService


class InvitationServiceActor extends Actor with InvitationService {
  def receive: Receive = runRoute(invitationRoutes)

  def actorRefFactory: ActorRefFactory = context
}

trait InvitationService extends HttpService with LazyLogging {

  import pl.akomar.domain.InviteeFormatter._
  import spray.httpx.SprayJsonSupport._

  val invitationRoutes =
    path("invitation") {
      get {
        complete(List(Invitee("John Smith", "john@smith.mx")))
      } ~
        post {
          entity(as[Invitee]) { invitee =>
            logger.info(s"Received invitee: $invitee")
            complete(StatusCodes.Created)
          }
        }
    }
}
