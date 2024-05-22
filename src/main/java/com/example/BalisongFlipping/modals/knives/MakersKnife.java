package com.example.BalisongFlipping.modals.knives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="ProductionKnives")
public class MakersKnife extends Knife {
    // members
    private double price;
    private Boolean discontinued;

}
