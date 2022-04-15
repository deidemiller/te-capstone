import axios from "axios";
const http = axios.create({
  baseURL: "http://localhost:8080",
});

export default {
  list() {
    return http.get("/potholes");
  },
  add(pothole) {
    return http.post('/potholes/add', pothole);
  },
  listPending() {
    return http.get('/potholes/pending');
  },
  listAll() {
    return http.get('/potholes/all');
  },
  listRepaired() {
    return http.get('/potholes/repaired');
  },
  listScheduled() {
    return http.get('/potholes/scheduled');
  },
  listUnscheduled() {
    return http.get('/potholes/unscheduled');
  },
  delete(id) {
    return http.delete('/potholes/delete/'+id);
  },
  updatePending(pothole) {
    return http.put('/potholes/status/update', pothole);
  },
  updateRepairStatus(pothole) {
    return http.put('/potholes/repair_status/update', pothole);
  },
  repairedPothole(pothole) {
    return http.put('/potholes/complete', pothole);
  },
  countRepaired() {
    return http.get('/potholes/count/completed');
  },
  countScheduled() {
    return http.get('/potholes/count/scheduled');
  },
  countUnscheduled() {
    return http.get('/potholes/count/unscheduled');
  },
  getVerifiedPotholes() {
    return http.get('/potholes/verified');
  }
};
