package com.example.BalisongFlipping.modals.knives;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
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
    private String uuid;
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

    private Knife() {
        setUuid("");
        setName("");
        setSummary("");
        setWeight(0.0);
        setOverallLength(0.0);
        setBladeLength(0.0);
        setHandleLength(0.0);
        setBladeMaterial(BladeMaterial.UNKNOWN);
        setBladeFinish(BladeFinish.UNKNOWN);
        setBladeShape(BladeShape.OTHER);
        setBladeType(BladeType.OTHER);
        setHandleFinish(HandleFinish.UNKNOWN);
        setHandleMaterial(HandleMaterial.UNKNOWN);
        setHandleConstruction(HandleConstruction.UNKNOWN);
        setHardware(KnifeHardware.OTHER);
        setPinSystem(PinSystem.OTHER);
    }

    public Knife(String uuid, String name) {
        setUuid(uuid);
        setName(name);
        setSummary("");
        setWeight(0.0);
        setOverallLength(0.0);
        setBladeLength(0.0);
        setHandleLength(0.0);
        setBladeMaterial(BladeMaterial.UNKNOWN);
        setBladeFinish(BladeFinish.UNKNOWN);
        setBladeShape(BladeShape.OTHER);
        setBladeType(BladeType.OTHER);
        setHandleFinish(HandleFinish.UNKNOWN);
        setHandleMaterial(HandleMaterial.UNKNOWN);
        setHandleConstruction(HandleConstruction.UNKNOWN);
        setHardware(KnifeHardware.OTHER);
        setPinSystem(PinSystem.OTHER);
    }

    public Knife(String uuid, String name, String summary, double weight, double overallLength, double bladeLength, double handleLength,  BladeMaterial bladeMaterial, BladeFinish bladeFinish, BladeShape bladeShape, BladeType bladeType, HandleFinish handleFinish, HandleMaterial handleMaterial, HandleConstruction handleConstruction, KnifeHardware hardware, PinSystem pinSystem) {
        setUuid(uuid);
        setName(name);
        setSummary(summary);
        setWeight(weight);
        setOverallLength(overallLength);
        setBladeLength(bladeLength);
        setHandleLength(handleLength);
        setBladeMaterial(bladeMaterial);
        setBladeFinish(bladeFinish);
        setBladeShape(bladeShape);
        setBladeType(bladeType);
        setHandleFinish(handleFinish);
        setHandleMaterial(handleMaterial);
        setHandleConstruction(handleConstruction);
        setHardware(hardware);
        setPinSystem(pinSystem);
    }
}
