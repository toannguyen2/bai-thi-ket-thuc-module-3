package app.controller;

import app.config.PathConfig;
import app.dao.category.CategoryDAO;
import app.dao.category.ICategory;
import app.dao.product.IProduct;
import app.dao.product.ProductDAO;
import app.model.Category;
import app.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "add", urlPatterns = "/add")
public class AddProductServlet extends HomeServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name     = req.getParameter("name");
		String price    = req.getParameter("price");
		String total    = req.getParameter("total");
		String color    = req.getParameter("color");
		String desc     = req.getParameter("desc");
		String category = req.getParameter("category");

		Product product = new Product(name, Integer.valueOf(price), Integer.valueOf(total), color, desc, Integer.valueOf(category));

		IProduct iProduct = new ProductDAO();
		iProduct.insert(product);

		req.setAttribute("status", "add ok.!!");
		RequestDispatcher dispatcher = req.getRequestDispatcher(PathConfig.LAYOUT + "add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ICategory iProduct = new CategoryDAO();
		List<Category> lists = iProduct.findAlL();
		req.setAttribute("lists", lists);

		RequestDispatcher dispatcher = req.getRequestDispatcher(PathConfig.LAYOUT + "add.jsp");
		dispatcher.forward(req, resp);
	}
}
