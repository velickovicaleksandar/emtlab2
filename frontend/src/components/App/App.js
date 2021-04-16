import React,{Component} from 'react';
import Books from "../Books/BookList/books"
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import LibraryService from '../Repository/libraryRepository'
import Header from '../Header/header'

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
     books:[]
    }
  }
    render(){
      return (
          <Router>
            <Header/>
            <main>
              <div className="container">
                <Route path={"/books"} exact render={() =>
                    <Books books ={this.state.books}/>}/>
                  {/*<Route path={"/books/add"} exact render={() =>*/}
                  {/*    // <BookAdd books={this.state.books}*/}

                  {/*                onAddProduct={this.addBook}/>}/>*/}

              </div>
            </main>
          </Router>)


    }
  loadBooks = () => {
    LibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }
    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
  componentDidMount() {
      this.loadBooks();
  }

}
export default App;