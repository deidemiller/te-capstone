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
    public List<Pothole> getAllPotholes() {
        List<Pothole> potholeList = new ArrayList<>();

        String sql = "SELECT pothole_id, date_reported, latitude, longitude, image_location " +
                "FROM pothole";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Pothole pothole = new Pothole();
            pothole.setPotholeId(results.getInt("pothole_id"));
            pothole.setDateReported(results.getDate("date_reported"));
            pothole.setLatitude(results.getBigDecimal("latitude").setScale(6));
            pothole.setLongitude(results.getBigDecimal("longitude").setScale(6));
            pothole.setImageUrl(results.getString("image_location"));
            potholeList.add(pothole);
        }

        return potholeList;
    }

    @Override
    public void addPothole(Pothole pothole) {
        String sql = "INSERT INTO pothole (date_reported, latitude, longitude, image_location) " +
                "VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, pothole.getDateReported(), pothole.getLatitude(), pothole.getLongitude(), pothole.getImageUrl());

    }


}
