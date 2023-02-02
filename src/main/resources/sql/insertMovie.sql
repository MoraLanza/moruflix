INSERT INTO movies (title, overview, runtime, release_date, popularity)
VALUES (:TITLE, :RESUME, :RUNTIME, :RELEASE_DATE, :POPULARITY)
RETURNING movie_id;
