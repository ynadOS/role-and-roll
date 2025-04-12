package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String fileType;

    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    // Constructors
    public File() {}

    public File(Long id, String filename, String fileType, String fileUrl, Player player) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.player = player;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
