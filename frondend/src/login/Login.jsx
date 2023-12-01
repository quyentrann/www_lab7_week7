import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS
import "./login.scss";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleClickLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8080/accounts/login", {
        email: email,
        password: password,
      });

      if (response.data.role === "user") {
        navigate("/home", { state: { customer: response.data.customer } });
      } else if (response.data.role === "admin") {
        navigate("/dashboard");
      } else {
        toast.error("🦄 Email Hoặc Password Không Đúng!", {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      }
    } catch (error) {
      console.error("Error during login:", error);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card mt-5">
            <div className="card-body">
              <h5 className="card-title text-center">ĐĂNG NHẬP</h5>
              <div className="form-group">
                <label htmlFor="login-email">Email:</label>
                <input
                  type="email"
                  value={email}
                  id="login-email"
                  className="form-control"
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label htmlFor="login-password">Password:</label>
                <input
                  type="password"
                  value={password}
                  id="login-password"
                  className="form-control"
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
              <button className="btn btn-primary btn-block" onClick={handleClickLogin}>
                Đăng Nhập
              </button>
              <p className="mt-3 text-center">
                <Link to="/sign-up">Đăng ký tài khoản</Link>
              </p>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};

export default Login;
