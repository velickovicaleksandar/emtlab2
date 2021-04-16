import axios from '../custom-axios/axios'
const LibraryService={

    fetchBooks: () => {
        return axios.get("/books");
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/products/add", {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies,

        });




}}
export default LibraryService;