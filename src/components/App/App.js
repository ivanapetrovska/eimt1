import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from "../Header/Header";
import libraryService from "../../repository/libraryRepository";
import React, {Component} from "react";
import Books from "../Books/BooksList";
import BookAdd from "../Books/BookAdd";
import BookEdit from "../Books/BookEdit";
import Categories from "../Categories/CategoriesList";
import Author from "../Authors/AuthorsList";

class App extends Component{
    constructor(props) {
        super(props)
        this.state={
            books:[],
            categories:[],
            authors:[],
            selectedBook:{}
        }
    }
    render() {
        return(
            <BrowserRouter>
                <Header />
                <main>
                    <div className={'container'}>
                        <Routes>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>} />
                            <Route path={"/authors"} element={<Author authors={this.state.authors}/>} />
                            <Route path={"/books/add"} element={<BookAdd categories={this.state.categories}
                                                                         authors={this.state.authors}
                                                                         onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element={<BookEdit onEditBook={this.editBook}
                                                                               categories={this.state.categories}
                                                                               authors={this.state.authors}
                                                                               book={this.state.selectedBook}/>}/>

                            <Route path={"/books"} element={<Books books={this.state.books}
                                                                   onDelete={this.deleteBook}
                                                                   onEdit={this.getBook}
                                                                   onMarkAsTaken={this.markAsTakenBook}/>} />
                            <Route path={"/"} element={<Books books={this.state.books}/>} />


                        </Routes>
                    </div>
                </main>

            </BrowserRouter>
        )
    }

    loadBooks=()=>{
        libraryService.fetchBooks()
            .then((data)=>{
                this.setState({
                    books:data.data
                })
            });
    }
    loadCategories=()=>{
        libraryService.fetchCategories()
            .then((data)=>{
                this.setState({
                    categories:data.data
                })
            });
    }
    loadAuthors=()=>{
        libraryService.fetchAuthors()
            .then((data)=>{
                this.setState({
                    authors:data.data
                })
            });
    }
    getBook = (id) => {
        libraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    editBook = (id, name,category, author, availableCopies) => {
        libraryService.editBook(id, name,category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    addBook = (name,category, author, availableCopies) => {
        libraryService.addBook(name,category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    deleteBook = (id) => {
        libraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }
    markAsTakenBook = (id) => {
        libraryService.markAsTakenBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    componentDidMount() {
        this.loadCategories();
        this.loadAuthors();
        this.loadBooks();
    }
}

export default App;

