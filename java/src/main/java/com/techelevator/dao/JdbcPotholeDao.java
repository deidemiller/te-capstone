package com.techelevator.dao;

import com.techelevator.model.Pothole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao  implements PotholeDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcPotholeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Pothole> getAllOpenPotholes() {
        List<Pothole> potholeList = new ArrayList<>();

        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole " +
                "WHERE repair_status != 'completed'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            potholeList.add(pothole);
        }
        return potholeList;
    }

    @Override
    public void addPothole(Pothole pothole) {
        String sql = "INSERT INTO pothole (date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pothole.getDateReported(), pothole.getLatitude(), pothole.getLongitude(), pothole.getImageUrl(), pothole.getCrossStreet1(), pothole.getCrossStreet2(),pothole.getContactName(), pothole.getContactEmail(), pothole.getContactPhone());

    }

    @Override
    public void deletePothole(int potholeId) {
        String sql = "DELETE FROM pothole WHERE pothole_id = ?";
        jdbcTemplate.update(sql, potholeId);
    }

    @Override
    public void updatePotholeStatus(Pothole pothole) {
        String sql = "UPDATE pothole SET pending = ? WHERE pothole_id = ?";
        jdbcTemplate.update(sql, pothole.isPending(), pothole.getPotholeId());
    }

    @Override
    public List<Pothole> getPendingPotholes() {
        List<Pothole> pendingPotholes = new ArrayList<>();
        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole " +
                "WHERE pothole.pending = TRUE";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            pendingPotholes.add(pothole);
        }
        return pendingPotholes;
    }

    @Override
    public List<Pothole> getAllPotholes() {
        List<Pothole> potholeList = new ArrayList<>();

        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            potholeList.add(pothole);
        }
        return potholeList;


    }

    @Override
    public List<Pothole> getRepairedPotholes() {
        List<Pothole> pendingPotholes = new ArrayList<>();
        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole " +
                "WHERE pothole.repair_status = 'completed'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            pendingPotholes.add(pothole);
        }
        return pendingPotholes;
    }

    @Override
    public List<Pothole> getScheduledPotholes() {
        List<Pothole> pendingPotholes = new ArrayList<>();
        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole " +
                "WHERE pothole.repair_status = 'scheduled'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            pendingPotholes.add(pothole);
        }
        return pendingPotholes;
    }

    @Override
    public List<Pothole> getUnscheduledPotholes() {
        List<Pothole> pendingPotholes = new ArrayList<>();
        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location, cross_street_1, cross_street_2, contact_name, contact_email, contact_phone, " +
                "pending, severity, repair_status, repair_date " +
                "FROM pothole " +
                "WHERE pothole.repair_status = 'unscheduled' AND pothole.pending = false";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Pothole pothole = mapRowToPothole(results);
            pendingPotholes.add(pothole);
        }
        return pendingPotholes;
    }

    private Pothole mapRowToPothole(SqlRowSet results) {
        Pothole pothole = new Pothole();
        pothole.setPotholeId(results.getInt("pothole_id"));
        pothole.setDateReported(results.getDate("date_reported"));
        pothole.setLatitude(results.getBigDecimal("latitude").setScale(6));
        pothole.setLongitude(results.getBigDecimal("longitude").setScale(6));
        pothole.setImageUrl(results.getString("image_location"));
        pothole.setCrossStreet1(results.getString("cross_street_1"));
        pothole.setCrossStreet2(results.getString("cross_street_2"));
        pothole.setContactName(results.getString("contact_name"));
        pothole.setContactEmail(results.getString("contact_email"));
        pothole.setContactPhone(results.getString("contact_phone"));
        pothole.setPending(results.getBoolean("pending"));
        pothole.setSeverity(results.getInt("severity"));
        pothole.setRepairStatus(results.getString("repair_status"));
        if (results.getDate("repair_date") != null) {
            pothole.setRepairDate(results.getDate("repair_date"));
        }
        return pothole;
    }
}
