package pl.akomar

import akka.actor.ActorRefFactory
import org.specs2.mutable.Specification
import spray.http.StatusCodes
import spray.testkit.Specs2RouteTest
import spray.json._
import DefaultJsonProtocol._

class InvitationServiceSpec extends Specification with Specs2RouteTest with InvitationService {
  def actorRefFactory: ActorRefFactory = system

  "InvitationService" should {
    "return 'John Smith' with email for GET request to /invitation path" in {
      Get("/invitation") ~> invitationRoutes ~> check {
        responseAs[String].parseJson must beEqualTo( """[
                                                        {
                                                          "invitee": "John Smith",
                                                          "email": "john@smith.mx"
                                                        }
                                                        ]""".parseJson)
      }
    }

    "return HTTP 201 for POST request containing 'John Smith' sent to /invitation path" in {
      Post("/invitation", """{
                            |  "invitee": "John Smith",
                            |  "email": "john@smith.mx"
                            |}""") ~> invitationRoutes ~> check {
        status mustEqual StatusCodes.Created
      }
    }
  }
}
