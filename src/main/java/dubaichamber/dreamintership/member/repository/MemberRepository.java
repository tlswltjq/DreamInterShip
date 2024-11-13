package dubaichamber.dreamintership.member.repository;

import dubaichamber.dreamintership.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
