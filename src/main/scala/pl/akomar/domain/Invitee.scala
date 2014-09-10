package pl.akomar.domain

import spray.json.DefaultJsonProtocol

case class Invitee(invitee: String, email: String)

object InviteeFormatter extends DefaultJsonProtocol {
  implicit val inviteeFormat = jsonFormat2(Invitee)
}