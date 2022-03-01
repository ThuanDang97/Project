package com.example.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
public class Computer {

    @Id
    @Pattern(regexp = "^CP-{1}[\\d]{4}$", message = "ID không đúng định dạng CP-XXXX (X: 0-9)")
    private String computerId;

    @Pattern(regexp = "^[A-Z]{1}[\\d]{4}$", message = "Địa chỉ không đúng định dạng AXXXX (X: 0-9)")
    private String computerLocation;
    @DateTimeFormat()
    private String computerStartUsedDate;
    @NotNull
    private String computerWarrantyPeriod;
    @NotNull
    private String computerConfiguration;
    @NotNull
    private String computerIpLocal;

    @OneToMany(mappedBy = "computer", cascade = {CascadeType.ALL, CascadeType.REMOVE})
    Set<Order> orders;

    @NotNull
    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturerId", referencedColumnName = "manufacturerId")
    private Manufacturer manufacturer;

    @NotNull
    @ManyToOne(targetEntity = Status.class)
    @JoinColumn(name = "statusId", referencedColumnName = "statusId")
    private Status status;

    @NotNull
    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "typeId", referencedColumnName = "typeId")
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "computer_game",
            joinColumns = @JoinColumn(name = "computerId"),
            inverseJoinColumns = @JoinColumn(name = "gameId")
    )
    private Set<Game> games;

    public Computer() {
    }

    public Computer(String computerId, String computerLocation, String computerStartUsedDate, String computerWarrantyPeriod, String computerConfiguration, Set<Order> orders, Manufacturer manufacturer, Status status, Type type, Set<Game> games) {
        this.computerId = computerId;
        this.computerLocation = computerLocation;
        this.computerStartUsedDate = computerStartUsedDate;
        this.computerWarrantyPeriod = computerWarrantyPeriod;
        this.computerConfiguration = computerConfiguration;
        this.orders = orders;
        this.manufacturer = manufacturer;
        this.status = status;
        this.type = type;
        this.games = games;
    }

    public Computer(@Pattern(regexp = "^[CP-]{1}[\\d]{4}$", message = "ID không đúng định dạng CP-XXXX (X: 0-9)") String computerId, @NotNull String computerConfiguration, @NotNull String computerIpLocal, @Pattern(regexp = "^[A-Z]{1}[\\d]{4}$", message = "Địa chỉ không đúng định dạng AXXXX (X: 0-9)") String computerLocation, String computerStartUsedDate, @NotNull String computerWarrantyPeriod, @NotNull Manufacturer manufacturer, @NotNull Status status, @NotNull Type type) {
        this.computerId = computerId;
        this.computerLocation = computerLocation;
        this.computerStartUsedDate = computerStartUsedDate;
        this.computerWarrantyPeriod = computerWarrantyPeriod;
        this.computerConfiguration = computerConfiguration;
        this.computerIpLocal = computerIpLocal;
        this.manufacturer = manufacturer;
        this.status = status;
        this.type = type;
    }

    public String getComputerId() {
        return computerId;
    }

    public void setComputerId(String computerId) {
        this.computerId = computerId;
    }

    public String getComputerLocation() {
        return computerLocation;
    }

    public void setComputerLocation(String computerLocation) {
        this.computerLocation = computerLocation;
    }

    public String getComputerStartUsedDate() {
        return computerStartUsedDate;
    }

    public void setComputerStartUsedDate(String computerStartUsedDate) {
        this.computerStartUsedDate = computerStartUsedDate;
    }

    public String getComputerWarrantyPeriod() {
        return computerWarrantyPeriod;
    }

    public void setComputerWarrantyPeriod(String computerWarrantyPeriod) {
        this.computerWarrantyPeriod = computerWarrantyPeriod;
    }

    public String getComputerConfiguration() {
        return computerConfiguration;
    }

    public void setComputerConfiguration(String computerConfiguration) {
        this.computerConfiguration = computerConfiguration;
    }

    public String getComputerIpLocal() {
        return computerIpLocal;
    }

    public void setComputerIpLocal(String computerIpLocal) {
        this.computerIpLocal = computerIpLocal;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}

