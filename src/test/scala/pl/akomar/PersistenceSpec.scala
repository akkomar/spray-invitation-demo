package pl.akomar

import org.specs2.mutable.Specification
import pl.akomar.domain.Invitee


class PersistenceSpec extends Specification with H2DB {
  "H2 DB" should {
    "Return a list of previously added invitees" in {
      createDB()
      getInvitees() must beEmpty

      val testInvitee = Invitee("A B", "mail")
      addInvitee(testInvitee)
      getInvitees() must contain(testInvitee)
    }
  }
}
