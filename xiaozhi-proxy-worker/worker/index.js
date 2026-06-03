export default {
  async fetch(request) {
    const url = new URL(request.url)
    const target = new URL(url.pathname + url.search, "http://119.29.98.196")

    const headers = new Headers(request.headers)
    headers.set("Host", "119.29.98.196")
    headers.set("X-Forwarded-Proto", "https")
    headers.set("X-Forwarded-Host", url.host)

    const upgrade = request.headers.get("Upgrade")
    if (upgrade && upgrade.toLowerCase() === "websocket") {
      return fetch(target.toString(), {
        method: request.method,
        headers,
        body: request.body,
      })
    }

    return fetch(target.toString(), {
      method: request.method,
      headers,
      body: request.method === "GET" || request.method === "HEAD" ? undefined : request.body,
      redirect: "manual",
    })
  }
}
