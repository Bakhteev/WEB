<?php

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET');
header("Access-Control-Allow-Headers: X-Requested-With");

require __DIR__ . '/../vendor/autoload.php';

include __DIR__ . '/core/dbService.php';

const dbService = new DatabaseService();

if ($_SERVER['REQUEST_METHOD'] == "GET") {
    print_r(parse_url($_SERVER['REQUEST_URI'], PHP_URL_SCHEME));
    $path =  parse_url($_SERVER['REQUEST_URI'])['path'];
    if ($path == '/api/hit') {
        $script_start = microtime(true);
        if (isset($_GET['r']) && isset($_GET['x']) && isset($_GET['y'])) {
            if (is_numeric($_GET['x']) && is_numeric($_GET['y']) && is_numeric($_GET['r'])) {
                $xValue = floatval($_GET['x']);
                $yValue = floatval($_GET['y']);
                $rValue = floatval($_GET['r']);
                echo $xValue;
                responceCreator($xValue, $yValue, $rValue, $script_start);
            } else {
                http_response_code(400);
                echo 'Bad request';
                exit(400);
            }
        } else {
            http_response_code(400);
            echo 'Bad request';
            exit(400);
        }
    } else if ($path == '/api/history') {
        header('Content-Type: application/json');
        http_response_code(200);

        echo json_encode(dbService->getData());
    }
}



function hit($x, $y, $r)
{
    if (
        $x >= 0 &&
        $y >= 0 &&
        $x <= $r &&
        $y <= $r
    ) {
        return true;
    } elseif ($x < 0 && $y < 0) {
        return false;
    } elseif ($x >= 0 && $y <= 0) {
        if (-$y <= ($r / 2 - $x)) {
            return true;
        }
        return false;
    } elseif ($x <= 0 && $y >= 0) {
        if (($x ** 2 + $y ** 2) <= $r ** 2) {
            return true;
        }
        return false;
    }
}


function responceCreator($xValue, $yValue, $rValue, $script_start)
{
    header('Content-Type: application/json');
    http_response_code(201);

    $hit = hit($xValue, $yValue, $rValue);

    $hitted = $hit ? 'hitted' : 'miss';
    $currentTime = gmDate("H:i:s", time() + 3600 * (3 + date("I")));
    $execution_time = ceil((microtime(true) - $script_start) * 100000000) / 100;

    dbService->setData($xValue, $yValue, $rValue, $hitted, $currentTime, $execution_time);
    $data = dbService->getLast();
    echo json_encode($data);
}
