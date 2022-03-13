import axios from "axios";
import MovieCard from "components/MovieCard";
import Pagination from "components/Pagination";
import { useState } from "react";
import { MoviePage } from "types/movie";

import {BASE_URL} from "utils/requests";

function Listing() {

  const [pageNumber, setpageNumber] = useState(0);

  axios.get(`${BASE_URL}/movies?page=0&size=12`)
  .then((response) => {
    const data = response.data as MoviePage;
    console.log(data);
  });

  return (
    <>
      <Pagination />

      <div className="container">
        <div className="row">
          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>

          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>

          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>

          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>

          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>

          <div className="col-sm-6 col-lg-4 col-xl-3 mb-3">
            <MovieCard />
          </div>
        </div>
      </div>
    </>
  );
}

export default Listing;
