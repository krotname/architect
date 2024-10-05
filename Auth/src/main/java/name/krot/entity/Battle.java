package name.krot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "battles")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class Battle {
    @Id
    private String battleId;
    private String organizerId;

    @ElementCollection
    @CollectionTable(name = "battle_participants", joinColumns = @JoinColumn(name = "battle_id"))
    @Column(name = "participant_id")
    private List<String> participants;
}