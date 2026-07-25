import { useEffect, useState } from "react";
import api from "../services/api";

function Home() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    api.get("/test")
      .then((res) => setMessage(res.data.message))
      .catch(() => setMessage("Backend not connected"));
  }, []);

  return (
    <div style={{ padding: "40px" }}>
      <h1>Offline Smart Inventory Management System</h1>
      <h3>{message}</h3>
    </div>
  );
}

export default Home;