package pl.akomar

import pl.akomar.domain.Invitee
import spray.http.StatusCodes
import spray.routing.HttpService

trait InvitationService extends HttpService {

  import pl.akomar.domain.InviteeFormatter._
  import spray.httpx.SprayJsonSupport._

  val invitationRoutes =
    path("invitation") {
      get {
        complete(List(Invitee("John Smith", "john@smith.mx")))
      } ~
        post {
          complete(StatusCodes.Created)
        }
    }
}
