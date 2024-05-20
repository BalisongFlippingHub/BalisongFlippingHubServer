package com.example.BalisongFlipping.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class Knife {
    enum BladeMaterial {
        S35VN,
        D2,
        M339,
        UNKNOWN
    }

    enum HandleConstruction {
        CHANNEL,
        SANDWHICH,
        CHANWHICH,
        UNKNOWN
    }

    enum KnifeHardware {
        WASHERS,
        BUSHINGS,
        BEARINGS,
        PIVOTS,
        OTHER
    }

    enum PinSystem {
        PINLESS,
        ZENPINS,
        TANGPINS,
        OTHER
    }

    enum HandleMaterial {
        ALLUMINIUM_6061,
        ALLUMINIUM_7075,
        ALLUMINIUM,
        TITANIUM,
        STAINLESS_STEEL,
        UNKNOWN
    }

    enum HandleFinish {
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

    enum BladeFinish {
        STONEWASHED,
        MIRROR_POLISHED,
        RAW,
        ACID_WASHED,
        DUAL_TONE,
        DAMASCUS,
        UNKNOWN
    }

    enum BladeShape {
        SCHIMITAR,
        TANTO,
        BOWIE,
        SPEARPOINT,
        AMERICAN_TANTO,
        JAPANESE_TANTO,
        KUKRI,
        OTHER
    }

    enum BladeType {
        TRAINER,
        LIVE_BLADE,
        FALSE_EDGE,
        OTHER
    }

    private String UID;
    private Date dateAqquired;
    private String maker;
    private String name;
    private double price;
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

    private ArrayList<String> customizations;
}
