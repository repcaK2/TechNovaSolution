package TechNovaSolution.TechNovaSolution.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Data
@Entity
@Table(name = "user_app")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@NaturalId(mutable = true)
	private String email;
	private String password;
	private String role;
	private boolean isEnabled = false;
	private String zone;

	@PrePersist
	public void generateAuto(){
		if (this.role == null){
			this.role = "USER";
		}

		if(this.zone == null){
			this.zone = "unselectedZone";
		}
	}
}
