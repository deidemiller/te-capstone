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
  delete(pothole) {
    http.delete('potholes/delete', pothole);
  },
  updatePending(pothole) {
    http.put('/potholes/status/update', pothole);
  },
  updateRepairStatus(pothole) {
    http.put('/potholes/repair_status/update', pothole);
  },
  repairedPothole(pothole) {
    http.put('/potholes/complete', pothole)
  }

};
