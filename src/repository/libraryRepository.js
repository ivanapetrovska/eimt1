import axios from "../custom-axios/axios";

const libraryService={
    fetchBooks:()=>{
        return axios.get("/books/list");
    },
    fetchCategories:()=>{
        return axios.get("/categories/list");
    },
    fetchAuthors:()=>{
        return axios.get("/authors/list");
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    editBook: (id, name, category,author, availableCopies) => {
        return axios.post(`/books/edit/${id}`, {
            "name": name,
            "category":category,
            "author": author,
            "availableCopies":availableCopies
        });
    },
    addBook: (name, category,author, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category":category,
            "author": author,
            "availableCopies":availableCopies
        });
    },
    markAsTakenBook:(id)=>{
        return axios.put(`/books/mark/${id}`);
    }
}

export default libraryService;