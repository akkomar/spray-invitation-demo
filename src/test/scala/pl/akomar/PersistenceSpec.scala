package pl.akomar

import org.specs2.mutable.Specification
import pl.akomar.domain.Invitee


class H2DBInviteeRepositorySpec extends Specification {
  val repo = new H2DBInviteeRepository
  "H2 DB" should {
    "Return a list of previously added invitees" in {
      repo.createDB()
      repo.getInvitees() must beEmpty

      val testInvitee = Invitee("A B", "mail")
      repo.addInvitee(testInvitee)
      repo.getInvitees() must contain(testInvitee)
    }
  }
}
