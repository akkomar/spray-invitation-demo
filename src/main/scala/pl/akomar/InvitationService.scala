package pl.akomar

import spray.http.{StatusCodes, StatusCode}
import spray.routing.HttpService

trait InvitationService extends HttpService {
  val invitationRoutes =
    path("invitation") {
      get {
        complete {
          """[
            |  {
            |    "invitee": "John Smith",
            |    "email": "john@smith.mx"
            |  }
            |]"""
        }
      } ~
        post {
          complete(StatusCodes.Created)
        }
    }
}
