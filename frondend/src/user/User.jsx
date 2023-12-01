import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../user/user.scss";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";

const User = () => {
  const [user, setUser] = useState();
  const [id, setId] = useState("");
  const [accountId, setAccountId] = useState("");
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [phone, setPhone] = useState("");
  const [isUpdate, setIsUpdate] = useState(false);
  const [nav, setNav] = useState([
    {
      id: "user",
      nav: "Thông Tin Người Dùng",
    },
    {
      id: "order",
      nav: "Đơn Hàng Đã Đặt",
    },
  ]);
  const [navChosen, setNavChosen] = useState("user");
  const [productsChosen, setProductsChosen] = useState([]);

  let navigate = useNavigate();
  let location = useLocation();

  useEffect(() => {
    let getApiCustomer = async () => {
      let datas = await axios.get(
        `http://localhost:8080/accounts/get-by-email/${location.state.email}`
      );
      setUser(datas.data.customer);
      setId(datas.data.customer.custId);
      setName(datas.data.customer.custName);
      setEmail(datas.data.customer.email);
      setPhone(datas.data.customer.phone);
      setAddress(datas.data.customer.address);
      setRole(datas.data.role);
      setPassword(datas.data.password);
      setAccountId(datas.data.accountId);
    };
    getApiCustomer();
  }, [isUpdate]);

  let handleCLickUpdateUser = async () => {
    await axios.put(`http://localhost:8080/customers`, {
      custId: id,
      custName: name,
      email: email,
      phone: phone,
      address: address,
    });

    let datas = await axios.get(
      `http://localhost:8080/customers/${id}`
    );

    await axios.put(`http://localhost:8080/accounts`, {
      accountId: accountId,
      email: email,
      password: password,
      role : "user",
      customer: datas.data,
    });
    setIsUpdate(!isUpdate);

    toast.success('🦄 Cập Nhật Thông Tin Thành Công!', {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
      });

  };

  let handleClickNav = async (navId) => {
    setNavChosen(navId);
    if (navId === "order") {
      let datas = await axios.get(
        `http://localhost:8080/products/ordered-by-cust-${id}`
      );
      setProductsChosen(datas.data);
    }
  };

  return (
    <div className="user">
      <div className="nav-user">
        {nav.map((n) => (
          <span key={n.id} className="nav" onClick={() => handleClickNav(n.id)}>
            {n.nav}
          </span>
        ))}
        <button className="btn-back" onClick={() => navigate(-1)}>
          <i className="fa-solid fa-arrow-left icon-back"></i>Back
        </button>
        <button className="btn-sign-out" onClick={() => navigate("/")}>
          <i className="fa-solid fa-right-from-bracket icon-sign-out"></i>Sign
          out
        </button>
      </div>
      <div className="container-user">
        {navChosen === "user" ? (
          <>
            <h2 className="text-info-cust">THÔNG TIN NGƯỜI DÙNG </h2>
            <div className="form-customer">
              <div className="content-form">
                <label className="lblForm" htmlFor="name">
                  Tên Khách Hàng :{" "}
                </label>
                <input
                  value={name}
                  className="inputForm"
                  type="text"
                  name=""
                  id="name"
                  onChange={(e) => setName(e.target.value)}
                />
              </div>
              <div className="content-form">
                <label className="lblForm" htmlFor="address">
                  Địa Chỉ :{" "}
                </label>
                <input
                  value={address}
                  className="inputForm"
                  type="text"
                  name=""
                  id="address"
                  onChange={(e) => setAddress(e.target.value)}
                />
              </div>
              <div className="content-form">
                <label className="lblForm" htmlFor="email">
                  Email :{" "}
                </label>
                <input
                  value={email}
                  className="inputForm"
                  type="email"
                  name=""
                  id="email"
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="content-form">
                <label className="lblForm" htmlFor="password">
                  Password :{" "}
                </label>
                <input
                  value={password}
                  className="inputForm"
                  type="password"
                  name=""
                  id="password"
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>

              <div className="content-form">
                <label className="lblForm" htmlFor="role">
                  Role :{" "}
                </label>
                <input
                  value={role}
                  className="inputForm"
                  type="text"
                  name=""
                  id="role"
                  onChange={(e) => setRole(e.target.value)}
                />
              </div>

              <div className="content-form">
                <label className="lblForm" htmlFor="phone">
                  Số Điện Thoại :{" "}
                </label>
                <input
                  value={phone}
                  className="inputForm"
                  type="tel"
                  name=""
                  id="phone"
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
              <div className="content-form">
                <button
                  className="btn-update-user"
                  onClick={handleCLickUpdateUser}
                >
                  Cập Nhật
                </button>
              </div>
            </div>
          </>
        ) : (
          <>
            <h2 className="text-info-cust">ĐƠN HÀNG ĐÃ ĐẶT</h2>
            {productsChosen.map((pro, index) => {
              let date = pro.orderDate.split("-")
              return (
                <>
                  <span>Ngày Đặt Hàng : {`${date[2]}-${date[1]}-${date[0]}`}</span>
                  <div className="content-cart" key={index}>
                    <img
                      src={`/${pro.path}`}
                      alt=""
                      className="image-product-chosen"
                    />
                    <div className="infor">
                      <span className="name-product-chosen">{pro.name}</span>
                      <span className="price-product-chosen">
                        {new Intl.NumberFormat("vi-VN", {
                          style: "currency",
                          currency: "VND",
                        }).format(pro.prices)}
                      </span>
                      <input
                        value={pro.quantity}
                        type="number"
                        name=""
                        id=""
                        className="quantity-product-chosen"
                        min={0}
                        disabled
                      />
                    </div>
                  </div>
                </>
              );
            })}
          </>
        )}
      </div>
      <ToastContainer />
    </div>
  );
};

export default User;
