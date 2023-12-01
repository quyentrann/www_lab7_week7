import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS
import "../signUp/signUp.scss";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [confirm, setConfirm] = useState(false);
  const navigate = useNavigate();

  const handleChangeConfirmPassword = (value) => {
    setConfirm(password === value ? true : false);
    setPasswordConfirm(value);
  };

  const handleChangePassword = (value) => {
    setConfirm(passwordConfirm === value ? true : false);
    setPassword(value);
  };

  const handleClickSignUp = async () => {
    try {
      const emailExists = await axios.get(
        `http://localhost:8080/api/accounts/checkExist/${email}`
      );

      if (!emailExists.data) {
        const dataCust = await axios.get(
          "http://localhost:8080/api/customers/getID"
        );

        await axios.post("http://localhost:8080/api/customers", {
          custId: dataCust.data + 1,
          custName: "",
          email: email,
          phone: "",
          address: "",
        });

        const datasGetCust = await axios.get(
          `http://localhost:8080/api/customers/${dataCust.data + 1}`
        );

        const dataAccountId = await axios.get(
          "http://localhost:8080/api/accounts/getID"
        );

        const dataPostAccount = await axios.post(
          "http://localhost:8080/api/accounts",
          {
            accountId: dataAccountId.data + 1,
            email: email,
            password: password,
            customer: datasGetCust.data,
          }
        );

        if (dataPostAccount) {
          navigate("/");
        }
      } else {
        toast.error("🦄 Email đã tồn tại!", {
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
      console.error("Error during signup:", error);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card mt-5">
            <div className="card-body">
              <h5 className="card-title text-center">ĐĂNG KÝ</h5>
              <div className="form-group">
                <label htmlFor="sign-up-email">Email:</label>
                <input
                  type="email"
                  value={email}
                  id="sign-up-email"
                  className="form-control"
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label htmlFor="sign-up-password">Password:</label>
                <input
                  type="password"
                  value={password}
                  id="sign-up-password"
                  className="form-control"
                  onChange={(e) => handleChangePassword(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label htmlFor="sign-up-confirm-password">Confirm Password:</label>
                <input
                  type="password"
                  value={passwordConfirm}
                  id="sign-up-confirm-password"
                  className="form-control"
                  onChange={(e) => handleChangeConfirmPassword(e.target.value)}
                />
                {confirm ? (
                  <i
                    className="bi bi-check-circle-fill icon-confirm"
                    style={{ color: "green" }}
                  ></i>
                ) : (
                  <i
                    className="bi bi-x-circle-fill icon-confirm"
                    style={{ color: "red" }}
                  ></i>
                )}
              </div>
              <button
                className="btn btn-primary btn-block"
                onClick={handleClickSignUp}
              >
                Đăng Ký
              </button>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};

export default SignUp;
