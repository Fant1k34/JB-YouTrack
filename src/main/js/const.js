export const Status = {
    START: "Start",
    LOADING: "Loading",
    ERROR: "Error",
    SUCCESS: "Success",
};

const filterReposUrl = "http://127.0.0.1:8080/filter-repos-api";

export const getFilterReposFetcher = (data) =>
    fetch(filterReposUrl, {
        method: "POST",
        mode: "same-origin",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((res) => {
        if (!res.ok) {
          throw Error("Method returned not OK")
        }

        return res
    });
