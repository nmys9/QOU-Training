type ProductsType={
    id:number,
    name:string,
    icon:string
};


const productsURL="/api/products.json";

async function getProducts(): Promise<ProductsType[]> {
    const response=await fetch(productsURL);
    const products=await response.json();
    return  products;
}

async function showProducts():Promise<void>{
    const products=getProducts();
    console.log(products);
}


showProducts();