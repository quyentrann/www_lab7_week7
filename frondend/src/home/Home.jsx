import { useEffect, useState } from "react"
import "../home/home.scss"
import Product from "../products/Product"
import axios from "axios"
import { useLocation, useNavigate } from "react-router-dom"

const Home = () => {
    const [products, setProducts] = useState([])
    const [productsChosen, setProductsChosen] = useState([])
    const [customer, setCustomer] = useState()
    const [totalPages, setTotalPages] = useState([])
    const [pageChosen, setPageChosen] = useState(0)

    let location = useLocation()
    let navigate = useNavigate()

    useEffect(() => {
        let getApiProducts = async() => {
            let dataTotalPage = await axios.get("http://localhost:8080/products/get-total-pages");
            setTotalPages(Array.from({ length: dataTotalPage.data }, (_, index) => index + 1))
            let datas = await axios.get(`http://localhost:8080/products/information-products/${pageChosen}`);
            setProducts(datas.data)
            setCustomer(location.state.customer)
        }
        getApiProducts()
    }, [JSON.stringify(products), pageChosen])

    let chooseProduct = (product) => {
        let proExist = productsChosen.find(pro => pro.productId === product.productId)
        console.log(proExist);
        if(!proExist){
            setProductsChosen([...productsChosen, {...product, quantity : 1}])
        }
        else{
            proExist.quantity += 1
            setProductsChosen([...productsChosen])
        }
    }

    let handleClickCart = () => {
        navigate("/cart", {state : {
            productsChosen : productsChosen,
            customer : customer
        }})
    }
  return (
    <div className='container-home'>
         <text style={{paddingLeft: '1000px', color: "green", fontSize: "50px", backgroundColor: "#f7afaf", height: "200px", alignItems: "center", justifyContent: "center", fontWeight:"bold", paddingTop: "50px"}}>WELCOM TO TRENDY SHOP</text>
        <div className='cart'>
            <i className="fa-solid fa-cart-shopping icon-cart" onClick={handleClickCart}>
                <span className="quantity-product-chosen">{productsChosen.length}</span>
            </i>
            <i className="fa-solid fa-user icon-user" onClick={() => navigate("/user", {state : customer})}></i>
        </div>
        <div className='content-products'>
            {
                products.map(product => <Product key={product.productId} 
                product={product} chooseProduct={chooseProduct}/>)
            }
        </div>
        <div className="content-number-pages">
            {
                totalPages.map(page => (
                    <button key={page} className="btn-page" onClick={() => setPageChosen(page-1)}
                    style={{
                        backgroundColor : pageChosen === page-1 ? "#f78708" : 'white',
                        color : pageChosen === page-1 ? "white" : 'black'
                    }}
                    >
                        {page}
                    </button>
                ))
            }
        </div>
    </div>
  )
}

export default Home