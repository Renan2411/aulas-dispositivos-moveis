package com.example.fragments;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pessoa implements Parcelable {

    private String name;
    private Float altura;
    private Float peso;
    private Float imc;

    public Pessoa(String name, Float altura, Float peso) {
        this.name = name;
        this.altura = altura;
        this.peso = peso;
    }

    protected Pessoa(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            altura = null;
        } else {
            altura = in.readFloat();
        }
        if (in.readByte() == 0) {
            peso = null;
        } else {
            peso = in.readFloat();
        }
        if (in.readByte() == 0) {
            imc = null;
        } else {
            imc = in.readFloat();
        }
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

    public void calcularImc() {
        imc = peso /(altura * altura);
    }

    public Float getImc(){
        return imc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        if (altura == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(altura);
        }
        if (peso == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(peso);
        }
        if (imc == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(imc);
        }
    }
}
