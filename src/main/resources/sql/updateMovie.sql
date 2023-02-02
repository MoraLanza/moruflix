UPDATE movies SET
    title = :TITLE,
    popularity = :POPULARITY,
    release_date = :RELEASE_DATE,
    runtime = :RUNTIME,
    resume = :RESUME
WHERE movie_id = :MOVIE_ID
