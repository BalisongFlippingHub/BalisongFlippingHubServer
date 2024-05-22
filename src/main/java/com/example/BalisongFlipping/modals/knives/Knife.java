package com.example.BalisongFlipping.modals.knives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Knife {
    public enum BladeMaterial {
        S35VN,
        D2,
        M339,
        UNKNOWN
    }

    public enum HandleConstruction {
        CHANNEL,
        SANDWHICH,
        CHANWHICH,
        UNKNOWN
    }

    public enum KnifeHardware {
        WASHERS,
        BUSHINGS,
        BEARINGS,
        PIVOTS,
        OTHER
    }

    public enum PinSystem {
        PINLESS,
        ZENPINS,
        TANGPINS,
        OTHER
    }

    public enum HandleMaterial {
        ALLUMINIUM_6061,
        ALLUMINIUM_7075,
        ALLUMINIUM,
        TITANIUM,
        STAINLESS_STEEL,
        G10,
        UNKNOWN
    }

    public enum HandleFinish {
        STONEWASHED,
        TUMBLED,
        RAW,
        POLISHED,
        DAMASUCS,
        TIMASCUS,
        BEAD_BLASTED,
        ACID_WASHED,
        UNKNOWN
    }

    public enum BladeFinish {
        STONEWASHED,
        MIRROR_POLISHED,
        RAW,
        ACID_WASHED,
        DUAL_TONE,
        DAMASCUS,
        UNKNOWN
    }

    public enum BladeShape {
        SCHIMITAR,
        TANTO,
        BOWIE,
        SPEARPOINT,
        AMERICAN_TANTO,
        JAPANESE_TANTO,
        KUKRI,
        OTHER
    }

    public enum BladeType {
        TRAINER,
        LIVE_BLADE,
        FALSE_EDGE,
        OTHER
    }

    // members
    @Id
    private String uuid;

    @Indexed(unique = true)
    private String name;

    private String summary;
    private double weight;
    private double overallLength;
    private double bladeLength;
    private double handleLength;
    private BladeMaterial bladeMaterial;
    private BladeFinish bladeFinish;
    private BladeShape bladeShape;
    private BladeType bladeType;
    private HandleFinish handleFinish;
    private HandleMaterial handleMaterial;
    private HandleConstruction handleConstruction;
    private KnifeHardware hardware;
    private PinSystem pinSystem;


}
